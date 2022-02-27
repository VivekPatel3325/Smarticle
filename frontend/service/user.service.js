import { BehaviorSubject } from "rxjs";
import router from "next/router";
import {serverUrl} from "helpers/api";

const userSubject = new BehaviorSubject(
  process.browser && JSON.parse(localStorage.getItem("user"))
);

export const userService = {
  user: userSubject.asObservable(),
  get userValue() {
    return userSubject.value;
  },
  login,
  logout,
  register,
};

async function login(username, password) {
  const res = await (
    await fetch(`${serverUrl}/user/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ userName: username, pswd: password }),
    })
  ).json();
  userSubject.next(res);
  localStorage.setItem("user", JSON.stringify(res));
  return res;
}

async function register(user) {
  let data;
  try {
    data = await (
      await fetch(`${serverUrl}/user/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        // credentials: "include",
        body: JSON.stringify({
          firstName: user.firstName,
          lastName: user.lastName,
          userName: user.userName,
          emailID: user.emailID,
          pswd: user.pswd
        }),
      })
    ).json();
  } catch (err) {
    throw new Error (err);
  }
  return data;
}

async function logout() {
  localStorage.removeItem("user");
  userSubject.next(null);
  router.push("/login");
}
import { BehaviorSubject } from "rxjs";
import router from "next/router";
import { serverUrl } from "helpers/api";

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
  forgot,
  reset,
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
  if (res["statusCode"] !== 200) throw new Error ("Error in logging in");
  const token = res["data"]["jwt-token"];
  userSubject.next({
    username,
    token
  });
  localStorage.setItem("user", JSON.stringify({
    username,
    token
  }));
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
        body: JSON.stringify({
          firstName: user.firstName,
          lastName: user.lastName,
          userName: user.userName,
          emailID: user.emailID,
          pswd: user.pswd,
        }),
      })
    ).json();
  } catch (err) {
    throw new Error(err);
  }
  return data;
}

async function forgot(user) {
  let data;
  try {
    data = await (
      await fetch(`${serverUrl}/user/forgotPassword`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          emailID: user.emailID,
        }),
      })
    ).json();
  } catch (err) {
    throw new Error(err);
  }
  return data;
}

async function reset(user) {
  let data;
  try {
    data = await (
      await fetch(`${serverUrl}/user/resetPassword`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "jwt-token": `${user.token}`
        },
        body: JSON.stringify({
          pswd: user.pswd,
        }),
      })
    ).json();
  } catch (err) {
    throw new Error(err);
  }
  return data;
}

async function logout() {
  localStorage.removeItem("user");
  userSubject.next(null);
  router.push("/login");
}

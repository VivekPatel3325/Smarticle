import { BehaviorSubject } from "rxjs";
import router from "next/router";
import apiUrl from "helpers/api";

const baseUrl = `${apiUrl}/auth`;
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

async function login(email, password) {
  const res = await (
    await fetch(`${baseUrl}/authenticate`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
      body: JSON.stringify({ email, password }),
    })
  ).json();
  userSubject.next(res);
  localStorage.setItem("user", JSON.stringify(res));
  return res;
}

async function register(user) {
  const res = await (
    await fetch(`${baseUrl}/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
      body: JSON.stringify(user),
    })
  ).json();
}

async function logout() {
  localStorage.removeItem("user");
  userSubject.next(null);
  router.push("/login");
}

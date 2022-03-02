import { serverUrl } from "helpers/api";

const getAll = async (token) => {
  let res;
  if (token) {
    res = await (
      await fetch(`${serverUrl}/article/retrieveArticle?visibility=ALL`, {
        method: "GET",
        headers: {
          "jwt-token": `${token}`
        }
      })
    ).json();
  } else {
    res = await (
      await fetch(`${serverUrl}/article/retrieveArticle?visibility=1`, {
        method: "GET",
        headers: {
          "jwt-token": `${token}`
        }
      })
    ).json();
  }
  return res;
};

const post = async (post, token) => {
  const res = await (
    await fetch(`${serverUrl}/article/postarticle`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "jwt-token": `${token}`
      },
      body: JSON.stringify(post),
    })
  ).json();
  if (res["statusCode"] !== 200) throw new Error("Error in posting");
  return res;
}

export const postService = {
  post,
  getAll,
};

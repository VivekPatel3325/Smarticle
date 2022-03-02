import { serverUrl } from "helpers/api";

const getAll = async () => {
  const res = await (
    await fetch(`${serverUrl}/article/retrieveArticle?visibility=ALL`, {
      method: "GET"
    })
  ).json();
  return res;
};

const post = async (post) => {
  const res = await (
    await fetch(`${serverUrl}/article/postarticle`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(post),
    })
  ).json();
  if (res["statusCode"] !== 200) throw new Error("Error in logging in");
  return res;
}

export const postService = {
  post,
  getAll,
};

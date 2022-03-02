import { serverUrl } from "helpers/api";
import posts from "data/posts.json";

const getAll = () => posts;

export const postService = {
  post,
  getAll,
};

async function post(post) {
  console.log(post);
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

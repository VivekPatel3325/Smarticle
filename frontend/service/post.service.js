import { serverUrl } from "helpers/api";
import posts from "data/posts.json";
// post : {heading, content, visibility, tagid[]}
const create = async (post) => {
  // @todo
  console.log(post);
}
const getAll = () => posts;

export const postService = {
  create,
  getAll
}

import jwt from "jsonwebtoken";

/**
 * fetch all posts
 * if auth header found, send posts by author + public posts
 * if no auth header, send only public posts
 */
const posts = require("helpers/posts").default;
const users = require("helpers/users").default;

export default async function getPosts(req, res) {
  const token = req.headers.authorization?.split(" ")[1];
  if (token) {
    const secret = process.env.JWT_KEY_SECRET;
    const { id } = jwt.verify(token, secret, { algorithms: ["HS256"] });
    const user = users.fromId(id);
    res.status(200).json({
      posts: posts.fromAuthorOrPublic(user.emailID),
    });
  } else {
    res.status(200).json({
      posts: posts.onlyPublic(),
    });
  }
}
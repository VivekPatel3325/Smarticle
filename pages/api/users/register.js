import bcrypt from "bcryptjs/dist/bcrypt";
import users from "helpers/users";
export default async function register(req, res) {
  const {password, ...user} = req.body;
  if (users.fromEmail(user.email)) {
    throw new Error(`User with username ${user.email} already exists`);
  }
  user.hash = bcrypt.hashSync(password, 10);
  users.create(user);
  return res.status(200).json({});
}
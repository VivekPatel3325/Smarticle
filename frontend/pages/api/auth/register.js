import users from "helpers/users";
import { serverUrl } from "helpers/api";
export default async function register(req, res) {
  const {...user} = req.body;
  let data;
  try {
    users.create(user);
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
          pswd: user.hash
        }),
      })
    ).json();
  } catch (err) {
    throw new Error (err);
  }
  return res.status(200).json({ data });
}
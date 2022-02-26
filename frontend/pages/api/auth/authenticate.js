const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const users = require('helpers/users').default;

export default async function authenticate(req, res) {
  const { email, password } = req.body;
  const user = users.fromEmail(email)
  if (!(user && bcrypt.compareSync(password, user.hash))) {
    throw new Error ('email or password is incorrect');
  }
  const token = jwt.sign({ id: user.id }, process.env.JWT_KEY_SECRET, { expiresIn: '7d' });
  return res.status(200).json({
      id: user.id,
      email: user.email,
      token
  });
}

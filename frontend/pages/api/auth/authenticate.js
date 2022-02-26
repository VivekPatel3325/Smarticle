const jwt = require('jsonwebtoken');
const expressJwt = require('express-jwt');
const bcrypt = require('bcryptjs');
import getConfig from 'next/config';
const { serverRuntimeConfig } = getConfig();
const users = require('frontend/helpers/users').default;

export default async function authenticate(req, res) {
  const { email, password } = req.body;
  const user = users.fromEmail(email)
  if (!(user && bcrypt.compareSync(password, user.hash))) {
    throw new Error ('email or password is incorrect');
  }
  const token = jwt.sign({ sub: user.id }, serverRuntimeConfig.secret, { expiresIn: '7d' });
  return res.status(200).json({
      id: user.id,
      email: user.email,
      token
  });
}

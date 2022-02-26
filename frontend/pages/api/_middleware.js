const jwt = require('jsonwebtoken');
import { NextRequest, NextResponse } from 'next/server'

const whitelist = [
  '/api/getPosts',
  '/api/auth/authenticate',
  '/api/auth/register'
]

// @todo: fix middleware jwt verification
export async function middleware(req, ev) {
  const token = req.headers.get('Authorization')?.split(" ")[1];
  const secret = process.env.JWT_KEY_SECRET;
  if (!token && whitelist.includes(req.page.name)) {
    return NextResponse.next();
  }
  try {
    await jwt.verify(token, secret, {
      algorithms: ['HS256']
    })
  } catch (err) {
    console.log('error in verification', err);
    return new Response('Auth required', {
      status: 401
    })
  }
}

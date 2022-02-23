import { userService } from "service/user.service";
import { useEffect, useState } from "react";
import { useRouter } from 'next/router';

export default function useUser() {
  const router = useRouter();
  console.log(router);
  const [user, setUser] = useState({});
  useEffect(() => {
    if (
        !userService.userValue &&
        (router.asPath !== '/login' && router.asPath !== '/signup')
      ) {
        router.push('/login');
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
  useEffect(() => {
    setUser(userService.userValue)
  }, [])
  return user;
}
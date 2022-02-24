import { useState, useEffect } from "react";
import Main from "layouts/main";
import { useRouter } from "next/router";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";
import { userService } from "service/user.service";
import Link from "next/link";

const Signup = () => {
  const router = useRouter();
  const validationSchema = Yup.object().shape({
    email: Yup.string().required("Username is required"),
    password: Yup.string()
      .required("Password is required")
      .min(6, "Password must be at least 6 characters"),
  });
  const formOptions = { resolver: yupResolver(validationSchema) };
  const { register, handleSubmit, formState } = useForm(formOptions);
  const { errors } = formState;

  function onSubmit(user) {
    return userService
      .register(user)
      .then(() => {
        router.push("login");
      })
      .catch((e) => console.log(e));
  }

  const [isMatching, setIsMatching] = useState(false);
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  useEffect(() => {
    if (password === confirmPassword) setIsMatching(true);
    else setIsMatching(false);
  }, [password, confirmPassword]);

  return (
    <Main title="Signup">
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="grid grid-cols-1 gap-3 w-96">
          <div className="flex flex-col">
            <label htmlFor="email">Email</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="email"
              name="email"
              id="email"
              placeholder="Enter email"
              {...register("email")}
            />
          </div>
          <div className="flex flex-col">
            <label htmlFor="email">Password</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="password"
              name="password"
              id="email"
              minLength="8"
              placeholder="Password"
              {...register("password")}
            />
          </div>
          <div className="flex flex-col">
            <label htmlFor="email">Password</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="password"
              name="password"
              id="email"
              minLength="8"
              placeholder="Confirm password"
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
          </div>
          {!isMatching && (
            <div>Password strings do not match. enter again.</div>
          )}
          {errors.email && <div>{errors.email.message}</div>}
          {errors.password && <div>{errors.password.message}</div>}
          <button className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white mt-4 p-2">
            Signup
          </button>
          <div className="justify-center items-center flex flex-row font-semibold">
            <Link href="/login" passHref>
              <span className="hover:underline cursor-pointer">
                Already registered?
              </span>
            </Link>
          </div>
        </div>
      </form>
    </Main>
  );
};

export default Signup;

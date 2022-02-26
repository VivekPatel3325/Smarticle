import Link from "next/link";
import Main from "layouts/main";
import { useRouter } from "next/router";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";
import { userService } from "service/user.service";

const Login = () => {
  const router = useRouter();
  const validationSchema = Yup.object().shape({
    email: Yup.string().email().required("Enter your Email ID"),
    password: Yup.string()
      .required("Enter your Password")
      .min(8, "Invalid Password as it is less than 8 characters"),
  });
  const formOptions = { resolver: yupResolver(validationSchema) };
  const { register, handleSubmit, formState } = useForm(formOptions);
  const { errors } = formState;
  function onSubmit({ email, password }) {
    return userService
      .login(email, password)
      .then(() => {
        const returnUrl = router.query.returnUrl || "/";
        router.push(returnUrl);
      })
      .catch((e) => console.log(e));
  }
  return (
    <Main title="Login">
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="grid grid-cols-1 gap-3 w-full lg:w-96">
          <div className="flex flex-col">
            <label htmlFor="email">Email</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="email"
              name="email"
              id="email"
              placeholder="Email"
              {...register("email")}
            />
            <p className="text-red-600">{errors.email?.message}</p>
          </div>
          <div className="flex flex-col">
            <label htmlFor="password">Password</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="password"
              name="password"
              id="password"
              placeholder="Password"
              {...register("password")}
            />
            <p className="text-red-600">{errors.password?.message}</p>
          </div>
          <button
            className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white mt-4 p-2"
            type="submit"
          >
            Login
          </button>
          <div className="flex flex-row justify-between font-semibold">
            <Link href="/forgot" passHref>
              <span className="hover:underline cursor-pointer">
                Forgot Password?
              </span>
            </Link>
            <Link href="/signup" passHref>
              <span className="hover:underline cursor-pointer">Signup</span>
            </Link>
          </div>
        </div>
      </form>
    </Main>
  );
};

export default Login;
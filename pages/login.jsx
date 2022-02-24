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
    email: Yup.string().required("email is required"),
    password: Yup.string().required("Password is required"),
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
        <div className="grid grid-cols-1 gap-5 w-96">
          <input
            className="bg-slate-50 outline-none m-2 p-2 rounded-lg border-2 border-black"
            type="email"
            name="email"
            id="email"
            placeholder="Email"
            {...register("email")}
          />
          <div className="">{errors.email?.message}</div>
          <input
            className="bg-slate-50 outline-none m-2 p-2 rounded-lg border-2 border-black"
            type="password"
            name="password"
            id="password"
            placeholder="Password"
            {...register("password")}
          />
          <div className="">{errors.password?.message}</div>
          <button
            className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white m-2 p-2"
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

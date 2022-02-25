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
    userName: Yup.string()
      .required("Provide a user name")
      .matches(
        /^[A-Za-z][A-Za-z0-9]*$/,
        "Provide username which do not start with a number and contains no special characters or spaces"
      )
      .min(8, "Use 7 characters or more for your username"),
    firstName: Yup.string().required("Enter your first name"),
    lastName: Yup.string().required("Enter your last name"),
    email: Yup.string().email().required("Enter your Email ID"),
    password: Yup.string()
      .required("Create a password for your account")
      .min(8, "Use 8 characters or more for your password"),
    confirmpassword: Yup.string().oneOf([Yup.ref("password"), null]),
  });

  const formOptions = { resolver: yupResolver(validationSchema) };
  const { register, handleSubmit, formState, reset } = useForm(formOptions);
  const { errors } = formState;

  function onSubmit(user) {
    return userService
      .register(user)
      .then(() => {
        router.push("login");
      })
      .catch((e) => console.log(e));
  }
  return (
    <Main title="Signup">
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="grid grid-cols-1 gap-3 w-full lg:w-96">
          <div className="flex flex-col">
            <label htmlFor="userName">User Name</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="text"
              name="userName"
              id="userName"
              placeholder="Enter User Name"
              {...register("userName")}
            />
          </div>
          <p className="text-red-600">{errors.userName?.message}</p>
          <div className="flex flex-col">
            <label htmlFor="firstName">First Name</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="text"
              name="firstName"
              id="firstName"
              placeholder="Enter First Name"
              {...register("firstName")}
            />
          </div>
          <p className="text-red-600">{errors.firstName?.message}</p>
          <div className="flex flex-col">
            <label htmlFor="lastName">Last Name</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="text"
              name="lastName"
              id="lastName"
              placeholder="Enter Last Name"
              {...register("lastName")}
            />
            <p className="text-red-600">{errors.lastName?.message}</p>
          </div>
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
          <div className="flex flex-col">
            <label htmlFor="confirmpassword">Confirm Password</label>
            <input
              className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
              type="password"
              name="confirmpassword"
              id="confirmpassword"
              placeholder="Confirm password"
              {...register("confirmpassword")}
            />
            <p className="text-red-600">
              {errors.confirmpassword &&
                "Those passwords didn’t match. Please try again."}
            </p>
          </div>
          <button
            className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white mt-4 p-2"
            type="submit"
          >
            Signup
          </button>
          <button
            className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white mt-4 p-2"
            type="button"
            onClick={() =>
              reset({
                userName: "",
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                confirmpassword: "",
              })
            }
          >
            Reset
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

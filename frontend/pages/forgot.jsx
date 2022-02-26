import Main from "layouts/main";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";

const forgot = () => {
  const validationSchema = Yup.object().shape({
    email: Yup.string().email().required("Enter your Email ID"),
  });
  const formOptions = { resolver: yupResolver(validationSchema) };
  const { register, handleSubmit, formState } = useForm(formOptions);
  const { errors } = formState;
  function onSubmit() {}

  return (
    <Main title="Forgot Password">
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="grid grid-cols-1 gap-2 w-full lg:w-96">
          <label htmlFor="email">Enter registered email:</label>
          <input
            className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
            type="email"
            name="email"
            id="email"
            placeholder="Email"
            {...register("email")}
          />
          <p className="text-red-600">{errors.email?.message}</p>
          <button className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white p-2 mt-4">
            Submit
          </button>
        </div>
      </form>
    </Main>
  );
};

export default forgot;
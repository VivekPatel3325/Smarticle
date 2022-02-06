import Link from "next/link";
import Main from "layouts/main";

const login = () => {
  return (
    <Main title="Login">
      <div className="grid grid-cols-1 gap-5 w-96">
        <input
          className="bg-slate-50 outline-none m-2 p-2 rounded-lg border-2 border-black"
          type="email"
          name="email"
          id="email"
          placeholder="Email"
          required
        />
        <input
          className="bg-slate-50 outline-none m-2 p-2 rounded-lg border-2 border-black"
          type="password"
          name="password"
          id="password"
          placeholder="Password"
          minLength="8"
          pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
          required
        />
        <button className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white m-2 p-2">
          Submit
        </button>
        <div className="flex flex-row justify-between font-semibold">
          <Link href="/forgot" passHref>
            <span className="hover:underline cursor-pointer">
              Forgot Password?
            </span>
          </Link>
          <Link href="/signup" passHref>
            <span className="hover:underline cursor-pointer">
              Signup
            </span>
          </Link>
        </div>
      </div>
    </Main>
  );
};

export default login;

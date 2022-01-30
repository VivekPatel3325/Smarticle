import Head from "next/head";
import Link from "next/link";
import styles from "../styles/Home.module.css";

const Login = () => {
  return (
    <div className={styles.container}>
      <Head>
        <title>Login</title>
      </Head>
      <main className={styles.main}>
        <div className="bg-gray-200 rounded-2xl shadow-2xl flex max-w-4xl">
          <div className="p-5">
            <div>
              <p className="text-1xl font-bold">Hello :)</p>
            </div>
            <div className="py-10">
              <p className="text-2xl font-bold mb-1">Login</p>
            </div>
            <div className="mb-14">
              <div className="flex flex-col items-center mb-4">
                <div className="bg-slate-50 w-64 p-2 rounded-full flex items-center">
                  <input
                    className="bg-slate-50 outline-none flex-1 ml-5"
                    type="email"
                    name="email"
                    id="email"
                    placeholder="Email"
                    required
                  />
                </div>
              </div>
              <div className="flex flex-col items-center mb-4">
                <div className="bg-slate-50 w-64 p-2 rounded-full flex items-center">
                  <input
                    className="bg-slate-50 outline-none flex-1 ml-5"
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Password"
                    minlength="8"
                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                    required
                  />
                </div>
              </div>
            </div>
            <div className="mb-10">
              <Link href="/">
                <button className="border-black border-2 rounded-full px-7 py-2 font-semibold hover:bg-black hover:text-white">
                  Login
                </button>
              </Link>
            </div>
            <div className="space-x-14 lg:space-x-20">
              <Link href="/Forgot">
                <a className="border-black font-semibold">Forget Password?</a>
              </Link>
              <Link href="/Signup">
                <a className="border-black font-semibold">Signup</a>
              </Link>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Login;

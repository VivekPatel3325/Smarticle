import Head from "next/head";
import Link from "next/link";
import styles from "../styles/Home.module.css";

const Signup = () => {
  return (
    <div className={styles.container}>
      <Head>
        <title>Signup</title>
      </Head>
      <main className={styles.main}>
        <div className="bg-gray-200 rounded-2xl shadow-2xl flex max-w-4xl">
          <div className="p-5">
            <div>
              <p className="text-1xl font-bold">Welcome :)</p>
            </div>
            <div className="py-10">
              <p className="text-2xl font-bold mb-1">Signup</p>
            </div>
            <div className="mb-14">
              <div className="flex flex-col items-center mb-4">
                <div className="bg-slate-50 w-64 p-2 rounded-full flex items-center">
                  <input
                    className="bg-slate-50 outline-none flex-1 ml-5"
                    type="text"
                    id="Username"
                    placeholder="Username"
                    required
                  />
                </div>
              </div>
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
                    minLength="8"
                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                    required
                  />
                </div>
              </div>
              <div className="flex flex-col items-center">
                <div className="bg-slate-50 w-64 p-2 rounded-full flex items-center">
                  <input
                    className="bg-slate-50 outline-none flex-1 ml-5"
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Confirm Password"
                    minLength="8"
                    required
                  />
                </div>
              </div>
            </div>
            <div>
              <Link href="/Signup">
                <button className="border-black border-2 rounded-full px-7 py-2 font-semibold hover:bg-black hover:text-white">
                  Signup
                </button>
              </Link>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Signup;

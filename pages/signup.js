import Head from "next/head";
import {useState, useEffect} from "react";

const Signup = () => {
  const [isMatching, setIsMatching] = useState(false);
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  useEffect(() => {
    if (password === confirmPassword) setIsMatching(true);
    else setIsMatching(false);
  }, [password, confirmPassword])
  return (
    <div>
      <title>Signup</title>
      <div>
        <div className="bg-gray-200 rounded-2xl shadow-2xl flex max-w-4xl">
          <div className="p-5">
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
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
              </div>
              <div className="flex flex-col items-center">
                <div className="bg-slate-50 w-64 p-2 rounded-full flex items-center">
                  <input
                    className="bg-slate-50 outline-none flex-1 ml-5"
                    type="password"
                    name="password"
                    id="confirm-password"
                    placeholder="Confirm Password"
                    minLength="8"
                    required
                    onChange={(e) => setConfirmPassword(e.target.value)}
                  />
                </div>
              </div>
            </div>

            {!isMatching && <div>Password strings do not match. enter again.</div>}

            <div>
              <button className="border-black border-2 rounded-full px-7 py-2 font-semibold hover:bg-black hover:text-white">
                Signup
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Signup;

import { useState, useEffect } from "react";
import Main from "layouts/main";

const Reset = () => {
  const [isMatching, setIsMatching] = useState(false);
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  useEffect(() => {
    if (password === confirmPassword) setIsMatching(true);
    else setIsMatching(false);
  }, [password, confirmPassword]);
  return (
    <Main title="Reset">
      <div className="grid grid-cols-1 gap-3 w-96">
        <div className="flex flex-col">
          <label htmlFor="email">Password</label>
          <input
            className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
            type="password"
            name="password"
            id="email"
            placeholder="Password"
            minLength="8"
            pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="flex flex-col">
          <label htmlFor="email">Password</label>
          <input
            className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
            type="password"
            name="password"
            id="email"
            placeholder="Confirm password"
            minLength="8"
            pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <div className="flex flex-col">
          <label htmlFor="email">Code</label>
          <input
            className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
            type="text"
            name="text"
            id="code"
            placeholder="Enter Code"
            required
          />
        </div>
        {!isMatching && <div>Password strings do not match. enter again.</div>}
        <button className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white mt-4 p-2">
          Reset Password
        </button>
      </div>
    </Main>
  );
};

export default Reset;

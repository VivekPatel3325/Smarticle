import Main from "layouts/main";

const forgot = () => {
  return (
    <Main title="Forgot Password">
      <div className="grid grid-cols-1 gap-2">
      <label htmlFor="email">
        Enter registered email:
      </label>
      <input
        className="bg-slate-50 outline-none m-2 p-2 rounded-lg border-2 border-black"
        type="email"
        name="email"
        id="email"
        placeholder="Email"
        required
      />
      </div>
    </Main>
  );
};

export default forgot;

import Main from "layouts/main";

const forgot = () => {
  return (
    <Main title="Forgot Password">
      <div className="grid grid-cols-1 gap-2 w-96">
        <label htmlFor="email">Enter registered email:</label>
        <input
          className="bg-slate-50 outline-none p-2 rounded-lg border-2 border-black"
          type="email"
          name="email"
          id="email"
          placeholder="Email"
          required
        />
        <button className="border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white p-2 mt-4">
          Submit
        </button>
      </div>
    </Main>
  );
};

export default forgot;

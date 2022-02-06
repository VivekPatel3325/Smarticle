const forgot = () => {
  return (
    <div>
      <div>
        <div className="bg-gray-200 rounded-2xl shadow-2xl flex max-w-4xl">
          <div className="p-5">
            <div className="mb-10">
              <p className="text-1xl font-bold">Provide Registered Email</p>
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
            </div>
            <div className="mb-10">
              <button className="border-black border-2 rounded-full px-7 py-2 font-semibold hover:bg-black hover:text-white">
                Send Reset Link
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default forgot;

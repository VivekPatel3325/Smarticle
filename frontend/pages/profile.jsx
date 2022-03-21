import Main from "layouts/main";
import useTags from "hooks/useTags";
import { useState } from "react";
import Select from "react-select";

export default function Profile() {
  const [tags, setTags] = useState([]);
  const handleTags = (tags) => setTags(tags);
  const options = useTags();
  return (
    <Main title="Profile">
      <div className="grid grid-cols-1 gap-3 w-full lg:w-96">
        <div className="flex flex-row">
          <label htmlFor="userName" className="mr-2">
            User Name
          </label>
          <input
            className="bg-slate-50 mr-16 outline-none p-2 rounded-lg border-2 border-black"
            type="text"
            name="userName"
            id="userName"
            placeholder="Kavan2708"
            disabled
          />
        </div>
        <div className="flex flex-row">
          <label htmlFor="firstName" className="mr-2">
            First Name
          </label>
          <input
            className="bg-slate-50 mr-2 outline-none p-2 rounded-lg border-2 border-black"
            type="text"
            name="firstName"
            id="firstName"
            placeholder="Kavan"
          />
          <button
            className="text-base border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white w-20 h-7"
            type="submit"
          >
            Update
          </button>
        </div>
        <div className="flex flex-row">
          <label htmlFor="lastName" className="mr-2">
            Last Name
          </label>
          <input
            className="bg-slate-50 mr-2 outline-none p-2 rounded-lg border-2 border-black"
            type="text"
            name="lastName"
            id="lastName"
            placeholder="Patel"
          />
          <button
            className="text-base border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white w-20 h-7"
            type="submit"
          >
            Update
          </button>
        </div>
        <div className="flex flex-row">
          <label htmlFor="emailID" className="lg:mr-6 md:mr-6 mr-3">
            Email ID
          </label>
          <input
            className="bg-slate-50 mr-2 outline-none p-2 rounded-lg border-2 border-black"
            type="email"
            name="emailID"
            id="emailID"
            placeholder="kavanpatel99@gmail.com"
          />
          <button
            className="text-base border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white w-20 h-7"
            type="submit"
          >
            Update
          </button>
        </div>
        <div className="flex flex-row">
          <label htmlFor="lastName" className="mr-2">
            Preference
          </label>
          <div className="w-48 mr-3">
            <Select
              options={options}
              isMulti
              placeholder="Select Tags"
              id="tags"
              instanceId={"tags"}
              onChange={handleTags}
            />
          </div>
          <button
            className="text-base border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white w-16 h-7"
            type="submit"
          >
            Save
          </button>
        </div>
      </div>
    </Main>
  );
}

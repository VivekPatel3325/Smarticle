import Main from "layouts/main";
import PostEditor from "components/PostEditor";
import Select from "react-select";

const Post = () => {
  const options = [
    { label: "one", value: 1 },
    { label: "two", value: 2 },
    { label: "three", value: 3 },
    { label: "four", value: 4 },
    { label: "five", value: 5 },
    { label: "six", value: 6 },
    { label: "seven", value: 7 },
    { label: "eight", value: 8 },
    { label: "nine", value: 9 },
  ];

  return (
    <Main title="Post Article">
      <div className="sm:w-3/4 w-auto">
        <PostEditor />
        <div className="flex flex-row justify-between mt-6">
          <div>
            <input
              className="mr-2"
              type="checkbox"
              id="checkbox1"
              name="checkbox1"
              value="public"
            />
            <label htmlFor="checkbox1">Make Public</label>
          </div>
          <div className="w-1/2">
            <Select
              options={options}
              isMulti
              placeholder="Select Tags"
              id="tags"
              instanceId={"tags"}
            />
          </div>
        </div>
      </div>
    </Main>
  );
};

export default Post;

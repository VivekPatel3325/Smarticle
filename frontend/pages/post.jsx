import Main from "layouts/main";
import PostEditor from "components/PostEditor";
import Creatable from "react-select/creatable";
import { useState } from "react";
import useTags from "hooks/useTags";
import { postService } from "service/post.service";
import { toast } from "react-toastify";
import useUser from "hooks/useUser";
import { tagsService } from "service/tags.service";

const Post = () => {
  const user = useUser();
  const token = user.token;
  const [postHtml, setPostHtml] = useState("");
  const [tags, setTags] = useState([]);
  const [visibility, setVisibility] = useState(false);
  const [heading, setHeading] = useState("");
  const options = useTags();
  const handleEditor = (e) => setPostHtml(e.editor.getHTML());
  const handleTitle = (e) => setHeading(e.target.value);
  const handleVisibility = (e) => setVisibility((current) => !current);
  const handleTags = (tags) => setTags(tags);
  const handleSubmit = async () => {
    const newTags = tags.filter((t) => t.__isNew__);
    await tagsService.createNew(newTags);
    await postService
      .post({
        heading,
        content: postHtml,
        tagId: tags.map((t) => t.value),
        visibility,
      }, token)
      .then((data) => {
        console.log("data", data);
        if (data && data["statusCode"] !== 200) {
          toast.error(`Error: ${JSON.stringify(data["message"])}`);
        } else {
          toast.success("Article Posted successfully");
        }
      })
      .catch((e) => {
        console.log(e);
        toast.error("Error while Posting");
      });
  };
  return (
    <Main title="Post Article">
      <div className="sm:w-full w-auto mt-5">
        <div className="text-center">
          <label htmlFor="heading" className="text-xl mr-5 font-bold">
            Post Title
          </label>
          <input
            type="text"
            name="heading"
            id="heading"
            onChange={handleTitle}
            className="focus:outline-none prose-stone leading-0.5 border-2 border-black rounded-md"
          />
        </div>
        <PostEditor handleChange={handleEditor} />
        <div className="flex flex-row justify-between mt-10 text-center">
          <div>
            <input
              className="mr-2"
              type="checkbox"
              id="checkbox1"
              name="checkbox1"
              value="public"
              onChange={handleVisibility}
            />
            <label htmlFor="checkbox1">Make Public</label>
          </div>
          <div className="w-1/2">
            <Creatable
              options={options}
              isMulti
              placeholder="Select Tags"
              id="tags"
              instanceId={"tags"}
              onChange={handleTags}
            />
          </div>
        </div>
        <div className="text-center">
          <button
            onClick={handleSubmit}
            className="text-base border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white mt-10 w-20 h-10"
            type="submit"
          >
            Post
          </button>
        </div>
      </div>
    </Main>
  );
};

export default Post;
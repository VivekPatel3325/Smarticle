import Main from "layouts/main";
import PostEditor from "components/PostEditor";
import Select from "react-select";
import { useState } from "react";
import useTags from "hooks/useTags";
import { postService } from "service/post.service";

const Post = () => {
  const [postHtml, setPostHtml] = useState("");
  const [tags, setTags] = useState([]);
  const [visibility, setVisibility] = useState(false);
  const [heading, setHeading] = useState("");
  const options = useTags();
  const handleEditor = (e) => setPostHtml(e.editor.getHTML())
  const handleTitle = (e) => setHeading(e.target.value)
  const handleVisibility = (e) => setVisibility((current) => !current);
  const handleTags = (tags) => setTags(tags);
  const handleSubmit = () => {
    postService.create({
      heading,
      content: postHtml,
      tagid: tags.map(t => t.value),
      visibility
    })
  }
  return (
    <Main title="Post Article">
      <div className="sm:w-3/4 w-auto">
        <div>
          <label htmlFor="heading">
            Title
          </label>
          <input
            type="text"
            name="heading"
            id="heading"
            onChange={handleTitle}
          />
        </div>
        <PostEditor handleChange={handleEditor} />
        <div className="flex flex-row justify-between mt-6">
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
            <Select
              options={options}
              isMulti
              placeholder="Select Tags"
              id="tags"
              instanceId={"tags"}
              onChange={handleTags}
            />
          </div>
        </div>
        <div>
          <button onClick={handleSubmit}>
            SUBMIT
          </button>
        </div>
      </div>
    </Main>
  );
};

export default Post;
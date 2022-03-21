import React, {useState, useEffect} from "react";
import Main from "layouts/main";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useRouter } from "next/router";
import moment from "moment";
import { postService } from "service/post.service";
import useUser from "hooks/useUser";
import { tagsService } from "service/tags.service";

const PostDetails = () => {
  const router = useRouter();
  const user = useUser();
  const {id} = router.query;
  const onClickLike = () => {console.log("Liked")}
  const [post, setPost] = useState({
    heading: "",
    content: "",
    createdOn: "",
    updatedOn: "",
    author: {
      firstname: "",
      lastname: "",
      username: ""
    },
    tags: []
  });
  useEffect(() => {
    const fetch = async () => {
      const post = await postService.getById(user?.token, id)
      const tags = await tagsService.getByIds(user?.token, post.tagId)
      setPost({
        heading: post.heading,
        content: post.content,
        createdOn: post.creationDate,
        updatedOn: post.updationDate,
        author: {
          firstname: post.userId?.firstName,
          lastname: post.userId?.lastName,
          username: post.userId?.userName
        },
        tags
      })
    }
    if (id) {
      fetch();
    }
  }, [id]);
  return (
    <Main>
          <div className="">
            <div className="flex flex-row justify-between">
              <div>
                <FontAwesomeIcon className="ml-1" icon="user" />
                <p className="inline text-gray-700 ml-2 font-medium text-lg">
                  {post.author.firstname}&nbsp;{post.author.lastname}
                </p>
              </div>
              <div>
                <FontAwesomeIcon className="ml-1" icon="calendar" />
                <p className="inline text-gray-700 ml-2 font-medium text-lg">
                  {moment(post.createdOn).format("MMM DD, YYYY")}
                </p>
              </div>
            </div>
            <div className="text-4xl my-4">
              {post.heading}
            </div>
            <article
              className=""
              dangerouslySetInnerHTML={{ __html: post.content }}
            />
            <div className="mt-4">
            {
              post.tags.map((tag) => {
                return (
                  <span className="px-1.5 bg-white border-black border-2 rounded-lg mr-2">
                    {tag.label}
                  </span>
                )}
              )
            }
            </div>
            <FontAwesomeIcon className="my-3 text-3xl opacity-50 hover:opacity-100 hover:cursor-pointer" icon="thumbs-up" onClick={onClickLike} />
      </div>
    </Main>
  );
};

export default PostDetails;
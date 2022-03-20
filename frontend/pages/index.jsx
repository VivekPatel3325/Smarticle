import Main from "layouts/main";
import moment from "moment";
import Select from "react-select";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import Link from "next/link";
import { useEffect, useState } from "react";
import useTags from "hooks/useTags";
import { postService } from "service/post.service";
import useUser from "hooks/useUser";
import { tagsService } from "service/tags.service";
library.add(faUser);
export default function Home() {
  const options = [
    { value: "Date", label: "By Date" },
    { value: "Likes", label: "By Likes" },
  ];
  const tags = useTags();
  const [posts, setPosts] = useState([]);
  const user = useUser();
  useEffect(() => {
    async function get() {
      const token = user?.token ?? null;
      setPosts(await postService.getAll(token));
    }
    get();
  }, [user?.token, user]);
  return (
    <Main title="Smarticle">
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 mt-10">
        <div className="lg:col-span-4 col-span-1">
          <div className="lg:sticky relative top-8">
            <div className="bg-gray-50 shadow-lg rounded-lg p-0 lg:p-8 pb-12 mb-8">
              <h3 className="text-xl mb-5 font-semibold border-b pb-4">
                Filter By
              </h3>
              <div>
                <h1 className="mb-1">Categories</h1>
                <Select
                  className="mb-5"
                  options={tags}
                  isMulti
                  placeholder="Select Tags"
                  id="tags"
                  instanceId={"tags"}
                />
              </div>
              <div>
                <h1 className="mb-1">Authors</h1>
                <Select
                  className="mb-5"
                  options={tags}
                  isMulti
                  placeholder="Select Authors"
                  id="tags"
                  instanceId={"tags"}
                />
              </div>
              <div>
                <h1 className="mb-1">Sort</h1>
                <Select
                  options={options}
                  placeholder="Sort By"
                  id="tags"
                  instanceId={"tags"}
                />
              </div>
              <div className="text-center">
                <button
                  className="text-base border-black border-2 rounded-md font-semibold hover:bg-black hover:text-white mt-10 w-20 h-10"
                  type="submit"
                >
                  Search
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="lg:col-span-8 col-span-1">
          {posts.map((post) => (
            <div
              className="bg-white shadow-lg rounded-lg p-0 lg:p-8 pb-12 mb-8"
              key={post.id}
            >
              <div>
                <h1 className="transition duration-700 text-center mb-5 cursor-pointer hover:text-gray-500 text-xl font-semibold">
                  <Link href={"/post/" + post.id}>{post.heading}</Link>
                </h1>
                <FontAwesomeIcon className="ml-3 lg:ml-1" icon="user" />
                <p className="inline align-middle text-gray-700 ml-3 font-medium text-lg">
                  {post.userId.firstName}&nbsp;{post.userId.lastName}
                </p>
                <div className="font-medium text-gray-700 mb-5 ml-2 lg:ml-0">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-6 w-6 inline mr-2 text-pink-500"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"
                    />
                  </svg>
                  <span className="align-middle">
                    {moment(post.creationDate).format("MMM DD, YYYY")}
                  </span>
                </div>
                <div className="mb-16 ml-3 lg:ml-0">
                  <article
                    className="line-clamp-6"
                    dangerouslySetInnerHTML={{ __html: post.content }}
                  ></article>
                </div>
                <div>
                  <p className="mb-7 italic">Likes: 1013</p>
                  <Link href={"/post/" + post.id}>
                    <span className="ml-3 lg:ml-0 cursor-pointer transition duration-500 ease transform hover:-translate-y-1 border-black border-2 rounded-md font-normal hover:bg-black hover:text-white mt-4 p-2">
                      Continue Reading
                    </span>
                  </Link>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </Main>
  );
}

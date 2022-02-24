import useUser from "hooks/useUser";
import Main from "layouts/main";
import { useState, useEffect } from "react";
import apiUrl from "helpers/api";
import moment from "moment";
import Select from "react-select";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import Link from "next/link";
library.add(faUser);

const baseUrl = `${apiUrl}/getPosts`;

export default function Home() {
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
  const [posts, setPosts] = useState([]);
  const user = useUser();
  const fetchPosts = async () => {
    const res = await (
      await fetch(baseUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${user?.token}`,
        },
        credentials: "include",
      })
    ).json();
    setPosts(res.posts);
  };
  useEffect(() => {
    if (user?.token) fetchPosts();
  }, [user?.token]);
  return (
    <Main title="Articles">
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 mt-10">
        <div className="lg:col-span-4 col-span-1">
          <div className="lg:sticky relative top-8">
            <div className="bg-gray-50 shadow-lg rounded-lg p-0 lg:p-8 pb-12 mb-8">
              <h3 className="text-xl mb-8 font-semibold border-b pb-4">
                Categories
              </h3>
              <div>
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
        </div>
        <div className="lg:col-span-8 col-span-1">
          {posts.map((post) => (
            <div className="bg-white shadow-lg rounded-lg p-0 lg:p-8 pb-12 mb-8">
              <div key={post.id}>
                <h1 className="transition duration-700 text-center mb-5 cursor-pointer hover:text-gray-500 text-xl font-semibold">
                  <Link href={"/post/" + post.id}>{post.title}</Link>
                </h1>
                <FontAwesomeIcon className="ml-1" icon="user" />
                <p className="inline align-middle text-gray-700 ml-3 font-medium text-lg">
                  {post.author}
                </p>
                <div className="font-medium text-gray-700 mb-5">
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
                    {moment(post.createdAt).format("MMM DD, YYYY")}
                  </span>
                </div>
                <div className="mb-5">
                  <article
                    className="line-clamp-6"
                    dangerouslySetInnerHTML={{ __html: post.post }}
                  ></article>
                </div>
                <div>
                  <Link href={"/post/" + post.id}>
                    <span className="cursor-pointer transition duration-500 ease transform hover:-translate-y-1 border-black border-2 rounded-md font-normal hover:bg-black hover:text-white mt-4 p-2">
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

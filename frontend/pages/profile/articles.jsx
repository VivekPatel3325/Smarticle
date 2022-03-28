import useUser from "hooks/useUser";
import Main from "layouts/main";
import { useState, useEffect } from "react";
import { postService } from "service/post.service";
import moment from "moment";
import Link from "next/link";

export default function Articles() {
  const [posts, setPosts] = useState([]);
  const [error, isError] = useState(false);
  const user = useUser();
  const token = user?.token;
  useEffect(() => {
    async function get() {
      try {
        setPosts(await postService.getByAuthor(token));
      } catch (err) {
        isError(true);
        console.log(err);
      }
    }
    if (token) get();
  }, [token]);
  return (
    <Main title="Articles">
      <div className="grid grid-cols-1 lg:grid-cols-6 gap-12 mt-10">
        <div className="lg:col-span-8 col-span-1">
          <div className="bg-white shadow-lg rounded-lg p-0 lg:p-8 pb-12 mb-8">
            {posts &&
              posts.map((post, key) => {
                return (
                  <div key={key}>
                    <h1 className="transition duration-700 mb-5 text-xl ml-2 lg:ml-0 font-semibold">
                      {post.heading}
                    </h1>
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
                    <div className="mb-5 ml-3 lg:ml-0">
                      <article
                        className="line-clamp-6"
                        dangerouslySetInnerHTML={{
                          __html: post.content.substring(0, 350),
                        }}
                      ></article>
                    </div>
                    <div className="mb-24">
                      <p className="ml-3 lg:ml-1 mb-7 italic">Likes: 1013</p>
                      <Link href={"/post/" + post.id}>
                        <span className="ml-3 lg:ml-0 cursor-pointer transition duration-500 ease transform hover:-translate-y-1 border-black border-2 rounded-md font-normal hover:bg-black hover:text-white mt-4 p-2">
                          Continue Reading
                        </span>
                      </Link>
                    </div>
                  </div>
                );
              })}
            {posts.length === 0 && <div>No articles posted yet</div>}
            {error && <div>There was an error</div>}
          </div>
        </div>
      </div>
    </Main>
  );
}

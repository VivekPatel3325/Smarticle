import React from "react";
import Main from "layouts/main";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { useRouter } from "next/router";
import { serverUrl } from "helpers/api";
import moment from "moment";
library.add(faUser);

const PostDetails = ({ res }) => {
  return (
    <Main>
      <div className="bg-white shadow-lg rounded-lg lg:p-8 pb-12 mb-8">
        <div className="px-4 lg:px-0">
          <div className="flex items-center mb-8 w-full">
            <div className="md:flex lg:mb-0 lg:w-auto mr-8 items-center">
              <FontAwesomeIcon className="ml-1" icon="user" />
              <p className="inline text-gray-700 ml-2 font-medium text-lg">
                {/* {res.userId.firstName}&nbsp;{res.userId.lastName} */}
              </p>
            </div>
            <div className="font-medium text-gray-700 absolute right-14 lg:right-44 md:right-36">
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
                {moment(res.creationDate).format("MMM DD, YYYY")}
              </span>
            </div>
          </div>
          <h1 className="mb-8 text-3xl font-semibold text-center">
            {res.heading}
          </h1>
          <article
            className="line-clamp-6 lg:ml-60 lg:w-1/2 ml-8 mb-20"
            dangerouslySetInnerHTML={{ __html: res.content }}
          ></article>
          <h1 className="text-center font-bold">Topic</h1>
          <p className="lg:ml-60 lg:w-1/2 ml-8 text-center">{res.tags}</p>
        </div>
      </div>
    </Main>
  );
};

export const getStaticPaths = async () => {
  const res = await await (
    await fetch(`${serverUrl}/article/getArticleById/?id=2`)
  ).json();

  const paths = await res.map((post) => {
    return {
      params: { id: post.id.toString() },
    };
  });

  return {
    paths,
    fallback: false,
  };
};

export const getStaticProps = async ({ params }) => {
  const res = await await (
    await fetch(`${serverUrl}/article/getArticleById/?id=${params.id}`)
  ).json();
  return {
    props: { res },
  };
};

export default PostDetails;

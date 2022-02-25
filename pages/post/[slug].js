import React from "react";
import Main from "../../layouts/main";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faUser } from "@fortawesome/free-solid-svg-icons";
library.add(faUser);
// import apiUrl from "helpers/api";
// import useUser from "hooks/useUser";
// import { useState, useEffect } from "react";
// import posts from "../../data/posts";

// export const getStaticPaths = async () => {
//   const res = await fetch("../../data/posts");
//   const data = res.json();
//   const paths = data.map((detail) => {
//     return {
//       params: { id: detail.id.toString() },
//     };
//   });
//   return {
//     paths,
//     fallback: false,
//   };
// };

// export const getStaticProps = async (context) => {
//   const id = context.params.id;
//   const res = await fetch("../../data/posts" + id);
//   const data = await res.json();
//   return {
//     props: { detail: data },
//   };
// };

const PostDetails = (detail) => {
  return (
    <Main>
      <div className="bg-white shadow-lg rounded-lg lg:p-8 pb-12 mb-8">
        <div className="px-4 lg:px-0">
          <div className="flex items-center mb-8 w-full">
            <div className="md:flex lg:mb-0 lg:w-auto mr-8 items-center">
              <FontAwesomeIcon className="ml-1" icon="user" />
              <p className="inline text-gray-700 ml-2 font-medium text-lg">
                Author Name
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
              <span className="align-middle">22-02-2022</span>
            </div>
          </div>
          <h1 className="mb-8 text-3xl font-semibold text-center">Post 1</h1>
          <p className="lg:ml-60 lg:w-1/2 ml-8 mb-20">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur
            scelerisque eleifend sem vel scelerisque. Vivamus imperdiet orci
            scelerisque venenatis ultrices. Donec ac leo eget est ultricies
            maximus. Nunc a mi convallis, aliquam nisi eget, posuere quam. Etiam
            nec tempus libero. Quisque tristique ornare lectus, tincidunt
            lobortis velit vulputate iaculis. Nulla bibendum augue et augue
            pellentesque pulvinar. Ut pellentesque vitae ante tempor finibus.
            Morbi accumsan, sem gravida scelerisque efficitur, nisi justo
            accumsan quam, non lobortis nisl nulla id massa. Vivamus vitae magna
            vitae erat molestie fermentum. Maecenas sed viverra neque, sit amet
            laoreet sem. Nam ornare finibus massa, sed tincidunt metus facilisis
            nec. Sed non tincidunt erat, vel efficitur odio. Quisque in libero
            magna. Suspendisse fringilla, turpis eu dapibus ornare, ligula dui
            ornare purus, quis finibus neque ante at purus. Donec et venenatis
            augue, sit amet consequat enim. Nullam a mauris eget ligula
            vulputate suscipit eget in libero. Donec molestie, libero ac feugiat
            malesuada, urna sapien tristique felis, quis blandit magna lorem
            condimentum sapien. Morbi eleifend tristique diam, in tempor tortor.
            Proin scelerisque lectus nec condimentum euismod. Suspendisse
            aliquam erat at orci ornare bibendum. Integer bibendum metus blandit
            ipsum iaculis ullamcorper. Nam rhoncus turpis vel sapien dapibus
            consectetur.
          </p>
          <h1 className="text-center font-bold">Topic</h1>
          <p className="lg:ml-60 lg:w-1/2 ml-8 text-center">
            Web Development, Information Technology
          </p>
        </div>
      </div>
    </Main>
  );
};

export default PostDetails;

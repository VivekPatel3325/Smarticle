import Head from "next/head";
import styles from "../styles/Home.module.css";
import Multiselect from "multiselect-react-dropdown";
import { useState } from "react";

const Post = () => {
  const data = [
    { Topic: "Information Technology", id: 1 },
    { Topic: "Cloud Computing", id: 2 },
    { Topic: "Machine Learning", id: 3 },
    { Topic: "Big Data", id: 4 },
  ];

  const [options] = useState(data);

  return (
    <div className={styles.container}>
      <Head>
        <title>Post</title>
      </Head>
      <main className={styles.main}>
        <p className="mb-5 text-4xl">Post Article</p>
        <div>
          <textarea className="outline-gray-600 resize-none rounded-md w-52 xl:w-600 h-80">
            Type Here....
          </textarea>
        </div>
        <div>
          <input
            className="mr-2"
            type="checkbox"
            id="checkbox1"
            name="checkbox1"
            value="public"
          />
          <label for="checkbox1">Make Public</label>

          <div className="flex xl:inline-flex xl:ml-44">
            Topic: &nbsp; &nbsp;
            <Multiselect
              className="rounded-md bg-f2f2f2 outline-gray-600 border-gray-300"
              options={options}
              displayValue="Topic"
            />
          </div>
        </div>
      </main>
    </div>
  );
};

export default Post;

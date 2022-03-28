import { serverUrl } from "helpers/api";

const getTweetDetails = async (token, id) => {
  let res;
  try {
    res = await (
      await fetch(`${serverUrl}/article/getTweetDataOfArticle?id=${id}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "jwt-token": `${token}`,
        },
      })
    ).json();
  } catch (err) {
    throw new Error("Error in fetching");
  }
  console.log(res);
  return res;
};

export const twitterService = {
  getTweetDetails,
};

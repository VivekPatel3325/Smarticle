import { serverUrl } from "helpers/api";

async function getAuthors() {
    let data;
    try {
      data = await (
        await fetch(`${serverUrl}/user/getUserDetailsPostedArticle`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        })
      ).json();
    } catch (err) {
      throw new Error(err);
    }
    if (data.length === 0 || !Array.isArray(data)) return [];
    data = data
      .map((author) => {
        return {
          label: author.firstName + " " + author.lastName,
          value: author.userName
        }
      })
      .filter((author) => author.label !== null);
    return data;
  }

  export const authorsService = {
    getAuthors,
  };
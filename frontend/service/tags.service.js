import { serverUrl } from "helpers/api";

async function getAll(token) {
  let data;
  try {
    data = await (
      await fetch(`${serverUrl}/tag/retriveTags`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "jwt-token": `${token}`
        },
      })
    ).json();
  } catch (err) {
    throw new Error(err);
  }
  data = data.map((tag) => {
    return {
      label: tag.tagName,
      value: tag.id
    }
  })
  return data;
}

export const tagsService = {
  getAll,
};

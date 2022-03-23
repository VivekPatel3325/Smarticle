import { serverUrl } from "helpers/api";

const getDetails = async (token) => {
  let res;
  res = await (
    await fetch(`${serverUrl}/user/getUserProfile`, {
      method: "GET",
      headers: {
        "jwt-token": `${token}`,
      },
    })
  ).json();
  return res;
};

const updateDetails = async (user, token) => {
  let data;
  console.log(user);
  data = await (
    await fetch(`${serverUrl}/user/updateUserProfile`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "jwt-token": `${token}`,
      },
      body: JSON.stringify(user),
    })
  ).json();
  if (data["statusCode"] !== 200) throw new Error("Error in updating details");
  return data;
}

export const profileService = {
  getDetails,
  updateDetails,
};

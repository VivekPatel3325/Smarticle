import Link from "next/link";

const Verify = () => {
  return (
    <div>
      <h1 className="text-3xl text-center mt-40 mb-8">
        Your Email has been Verified.
      </h1>
      <div className="justify-center items-center flex flex-row font-semibold">
        <Link href="/login" passHref>
          <span className="hover:underline cursor-pointer">
            Click to continue
          </span>
        </Link>
      </div>
    </div>
  );
};
export default Verify;

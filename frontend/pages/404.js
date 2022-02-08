import Link from "next/link";
import styles from "../styles/Home.module.css";

const NotFound = () => {
  return (
    <main className={styles.main}>
      <div className="not-found">
        <p className="font-bold text-5xl mb-2">404 Error</p>
        <p className="font-semibold text-2xl mb-2">Page Not Found</p>
        <Link href="/">
          <a className="a-link">Back to Home Page</a>
        </Link>
      </div>
    </main>
  );
};

export default NotFound;

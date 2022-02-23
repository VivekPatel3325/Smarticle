import Navbar from "components/Navbar"
import Footer from "components/Footer"
import { NextSeo } from "next-seo"

export default function MainCentered ({children, title}) {
  return (
    <>
      <NextSeo
        titleTemplate="%s | Smarticle"
        description="Smarticle"
        title={title}
      />
      <div className="min-h-screen flex flex-col items-center justify-center">
      <Navbar />
      <div className="flex-1 sm:px-32 px-12 py-12">
        {
          title &&
          <div className="font-bold text-2xl py-6">
            {title}
          </div>
        }
        {children}
      </div>
      <Footer />
      </div>
    </>
  )
}

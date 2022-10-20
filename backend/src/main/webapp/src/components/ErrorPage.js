const { useSearchParams } = require("react-router-dom");
const { default: Header } = require("./Header");
const { default: NotFoundContent } = require("./NotFoundContent");
const { default: UnauthorizedContent } = require("./UnauthorizedContent");


const ErrorPage = () => {
    const error = useSearchParams()[0].get("error")
    return (
        <div>
            <Header />
            {{
                '401': <UnauthorizedContent />,
                '404': <NotFoundContent />,
            }[error]}
        </div>
    );
}

export default ErrorPage;
const { useSearchParams } = require("react-router-dom");
const { default: NotFoundContent } = require("./NotFoundContent");
const { default: UnauthorizedContent } = require("./UnauthorizedContent");


const ErrorPage = () => {
    const error = useSearchParams()[0].get("error")
    return (
        <div>
            {{
                '401': <UnauthorizedContent />,
                '404': <NotFoundContent />,
            }[error]}
        </div>
    );
}

export default ErrorPage;
const { useSearchParams } = require("react-router-dom");
const { default: NotFoundContent } = require("./NotFoundContent");
const { default: UnauthorizedContent } = require("./UnauthorizedContent");
const { default: InternalServerError } = require("./InternalServerError");

const ErrorPage = () => {
  const error = useSearchParams()[0].get("error");
  return (
    <div>
      {
        {
          401: <UnauthorizedContent />,
          404: <NotFoundContent />,
          500: <InternalServerError />,
        }[error]
      }
    </div>
  );
};

export default ErrorPage;

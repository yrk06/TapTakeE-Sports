import ViewUserTeamForm from "./ViewUserTeamForm";
import $ from 'jquery'

const UpdateUserTeam = () => {
    const userTeam = $.deparam(window.location.search.slice(1));

    return (
        <ViewUserTeamForm update={true} userTeam={userTeam} />
    )
}

export default UpdateUserTeam

const { default: ViewTeamForm } = require("./ViewTeamForm");


const UpdateTeam = () => {
    const team = Object.fromEntries(new URLSearchParams(window.location.search));

    console.log(team);

    return (
        //TODO: verificar os items para realziar o update
        <ViewTeamForm update = {true} teamId= {team.idEquipe} teamName= {team.nomeTime} />
    )

}

export default UpdateTeam;

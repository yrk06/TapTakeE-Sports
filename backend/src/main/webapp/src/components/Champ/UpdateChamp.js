import ViewChampForm from "./ViewChampForm";
import $ from 'jquery'

const UpdateChamp = () => {
    const champ = $.deparam(window.location.search.slice(1));

    console.log(champ)

    return (
        <ViewChampForm update={true} champ={champ} />
    )
}

export default UpdateChamp

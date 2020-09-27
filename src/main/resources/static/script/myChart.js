// For a pie chart

let decodedCharData = JSON.parse(decodeHtml(chartData));
let chartLabel = [];
let chartValue = [];
for (let i = 0; i < decodedCharData.length; i++) {
    chartLabel[i] = decodedCharData[i].label;
    chartValue[i] = decodedCharData[i].value;
}

new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    data: {
        labels: chartLabel,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3E95CD", "#8E5EA2", "#3CBA9F"],
            data: chartValue
        }]
    },

    options: {
        title: {
            display: true,
            text: "Project Statuses"
        }
    }
});

function decodeHtml(html) {
    let txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}
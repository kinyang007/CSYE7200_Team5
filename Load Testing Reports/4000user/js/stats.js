var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "4000",
        "ok": "4000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "355",
        "ok": "355",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "36222",
        "ok": "36222",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "4014",
        "ok": "4014",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "9841",
        "ok": "9841",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1031",
        "ok": "1031",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1510",
        "ok": "1510",
        "ko": "-"
    },
    "percentiles3": {
        "total": "35945",
        "ok": "35945",
        "ko": "-"
    },
    "percentiles4": {
        "total": "36151",
        "ok": "36151",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 1571,
    "percentage": 39
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 1288,
    "percentage": 32
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1141,
    "percentage": 29
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "108.108",
        "ok": "108.108",
        "ko": "-"
    }
},
contents: {
"req_4000usertest-2a25f": {
        type: "REQUEST",
        name: "4000UserTest",
path: "4000UserTest",
pathFormatted: "req_4000usertest-2a25f",
stats: {
    "name": "4000UserTest",
    "numberOfRequests": {
        "total": "4000",
        "ok": "4000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "355",
        "ok": "355",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "36222",
        "ok": "36222",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "4014",
        "ok": "4014",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "9841",
        "ok": "9841",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1031",
        "ok": "1031",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1510",
        "ok": "1510",
        "ko": "-"
    },
    "percentiles3": {
        "total": "35945",
        "ok": "35945",
        "ko": "-"
    },
    "percentiles4": {
        "total": "36151",
        "ok": "36151",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 1571,
    "percentage": 39
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 1288,
    "percentage": 32
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1141,
    "percentage": 29
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "108.108",
        "ok": "108.108",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}

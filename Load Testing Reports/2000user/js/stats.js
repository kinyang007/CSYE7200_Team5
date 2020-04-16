var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2000",
        "ok": "2000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "176",
        "ok": "176",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1534",
        "ok": "1534",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "840",
        "ok": "840",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "290",
        "ok": "290",
        "ko": "-"
    },
    "percentiles1": {
        "total": "796",
        "ok": "796",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1024",
        "ok": "1024",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1520",
        "ok": "1520",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1527",
        "ok": "1527",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 1009,
    "percentage": 50
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 806,
    "percentage": 40
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 185,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "666.667",
        "ok": "666.667",
        "ko": "-"
    }
},
contents: {
"req_2000usertest-2e14a": {
        type: "REQUEST",
        name: "2000UserTest",
path: "2000UserTest",
pathFormatted: "req_2000usertest-2e14a",
stats: {
    "name": "2000UserTest",
    "numberOfRequests": {
        "total": "2000",
        "ok": "2000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "176",
        "ok": "176",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1534",
        "ok": "1534",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "840",
        "ok": "840",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "290",
        "ok": "290",
        "ko": "-"
    },
    "percentiles1": {
        "total": "796",
        "ok": "796",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1024",
        "ok": "1024",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1520",
        "ok": "1520",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1527",
        "ok": "1527",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 1009,
    "percentage": 50
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 806,
    "percentage": 40
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 185,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "666.667",
        "ok": "666.667",
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

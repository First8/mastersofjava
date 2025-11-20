## Scheduling a Job in the Greenest Zone 🌍🔋

As Java developers, we are increasingly aware of the environmental impact of our work. We strive to make decisions that not only improve performance but also reduce our carbon footprint. One of the ways we can contribute to a greener future is by choosing the right moment and location to run our jobs, so that they consume the least amount of energy possible.

Carbon intensity refers to the amount of carbon dioxide (CO₂) emissions produced per unit of energy consumed. In many regions, the carbon intensity of the electricity grid varies depending on the time of day and geographical location. By running a job at the right time in the right zone, we can minimize its environmental impact.

In the first part of the assignment, we have a job that should exactly run at a specific time. But we still want to execute this job as green as possible! The zones with lower carbon intensity are considered “greener,” and you should schedule the job to run in the zone with the lowest carbon emissions at the given timepoint.

The second part of this assignment is different, we have our application deployed in a specific datacenter, and will not be able to just run the job in a different part of the world this time! We are bound to our zone, but this time we are more flexible in the timestamp the job should run. Can you give us the "greenest" timestamp that we should run our job?

---------
This assignment is inspired by the open-source GreenScheduler, one of the projects of First8 Conclusion to improve the carbon footprint of the applications we and our customers develop. The GreenScheduler helps us execute our processes with carbon emissions in mind. See github.com/carbonintensityio/scheduler for more information.
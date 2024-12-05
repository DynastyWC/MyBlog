import axios from 'axios';

type GetResponse = {
    msg: string;
    code: number;
};

export async function checkEmp9601() {
    try {
        axios.defaults.withCredentials = true;
        //  const data: GetUsersResponse
        const {data, status} = await axios.get<GetResponse>(
            'http://127.0.0.1:9601/emp/checkEmp?staffAccount=1873889',
            {
                headers: {
                    // 'Content-Type': 'application/x-www-form-urlencoded'
                    Accept: 'application/json',
                    'withCredentials': true,
                },
            },
        );

        console.log(JSON.stringify(data, null, 4));

        //  "response status is: 200"
        console.log('response status is: ', status);
        return data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.log('error message: ', error.message);
            return error.message;
        } else {
            console.log('unexpected error: ', error);
            return 'An unexpected error occurred';
        }
    }
}

export async function saveEmp9601() {
    try {
        // const data: CreateUserResponse
        const {data, status} =
            await axios.post<GetResponse>(
                'http://127.0.0.1:9601/emp/save',
                {
                    "staffGender": 0,
                    "staffPhone": "18738890999",
                    "staffEntryTime": "2023-9-7",
                    "staffGraduateSchool": "天津大学",
                    "staffGraduateMajor": "软件工程",
                    "staffGraduateLevel": "硕士",
                    "staffQqAccount": "1778944002",
                    "staffWeixinAccount": "First_IDENTIDY",
                    "staffEmail": "study@163.com",
                    "staffMaritalStatus": 0,
                    "staffPregnancyStatus": 0,
                    "staffSuitColor": "pruple",
                    "staffSuitSize": "xl",
                    "staffRemark": "super_admin",
                    "staffPhotoFile": "d:/images/1.jpg",
                    "staffName": "dy1",
                    "staffSchool": "员工大学",
                    "staffDepartment": "管理部",
                    "staffRole": "干事",
                    "staffAccount": "18738890999"
                },
                {
                    headers: {
                        'Content-Type': 'application/json',
                        Accept: 'application/json',
                    },
                },
            );

        console.log(JSON.stringify(data, null, 4));

        console.log(status);

        return data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.log('error message: ', error.message);
            // error: AxiosError<any, any>
            return error.message;
        } else {
            console.log('unexpected error: ', error);
            return 'An unexpected error occurred';
        }
    }
}



groupmates = [
    {
        "name": "Иван",
        "surname": "Петров",
        "exams": ["История", "АиГ", "КТП"],
        "marks": [2, 4, 5]
    },
    {
        "name": "Иван",
        "surname": "Петров",
        "exams": ["История", "АиГ", "КТП"],
        "marks": [2, 4, 5]
    },
        {
        "name": "Иван",
        "surname": "Алексюк",
        "exams": ["История", "АиГ", "КТП"],
        "marks": [2, 3, 2]
    },
    {
        "name": "Кирилл",
        "surname": "Смирнов",
        "exams": ["Философия", "ИС", "КТП"],
        "marks": [5, 5, 5]
    },
    {
        "name": "Александр",
        "surname": "Иванов",
        "exams": ["Информатика", "ЭЭиС", "Web"],
        "marks": [4, 3, 5]
    },
    {
        "name": "Иван",
        "surname": "Петров",
        "exams": ["История", "АиГ", "КТП"],
        "marks": [4, 4, 4]
    }
]

def sort_students(students):
    out = [0 for i in range(len(students))]
    cnt = [0 for i in range(0, 31)]
    for student in students:
        cnt[int(sum(student['marks'])/len(student['marks'])*10)-20] += 1
    for i in range(1, len(cnt)):
        cnt[i] += cnt[i-1]
    for i in range(len(students)):
        current_state = int(sum(students[i]['marks']) / len(students[i]['marks']) * 10) - 20
        out[cnt[current_state] - 1] = students[i]
        cnt[current_state] -= 1
    return(out)

def avg_mark(mark):
    #sorted_list = sorted(groupmates, key=lambda x: sum(x['marks']) / len(x['marks']))
    sorted_list = sort_students(groupmates)
    g_students = []
    for student in sorted_list:
        if (sum(student['marks'])/len(student['marks']) > mark):
            g_students.append(student)
    return(g_students)

def print_students(students):
    for student in students:
        print(student["name"].ljust(15), student["surname"].ljust(10), str(student["exams"]).ljust(30), str(student["marks"]).ljust(20))
    return(0)

print_students(avg_mark(int(input('Enter mark: '))))